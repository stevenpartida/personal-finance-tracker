import api from "./client"
import type { MoneyFlowType } from "./categories";

export type Transaction = {
    id: string;
    categoryId: string;
    type: MoneyFlowType;
    amount: string;
    occurredOn: string;
    createdAt: string;
};

export async function listTransactions() {
    const res = await api.get<Transaction[]>("/transactions");
    return res.data
}

export async function createTransaction(payload: {
    categoryId: string;
    type: MoneyFlowType;
    amount: number;
    occurredOn: string;
}) {
    const res = await api.post<Transaction>("/transactions", payload);
    return res.data;
}