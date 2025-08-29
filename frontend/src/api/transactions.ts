import api from "./client"
import type { MoneyFlowType } from "./categories";
import type { Category } from "./categories"

export type Transaction = {
    id: string;
    categoryId: string;
    name: string;
    type: MoneyFlowType;
    amount: string;
    occurredOn: string;
    createdAt: string;
    category?: Category;

};

export async function listTransactions() {
    const res = await api.get<Transaction[]>("/transactions");
    return res.data
}

export async function createTransaction(payload: {
    categoryId: string;
    name: string;
    type: MoneyFlowType;
    amount: number;
    occurredOn: string;
}) {
    const res = await api.post<Transaction>("/transactions", payload);
    return res.data;
}