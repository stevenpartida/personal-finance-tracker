import api from "./client"

export type MoneyFlowType = "EXPENSE" | "INCOME";
export type Category = { id: string; name: string; type: MoneyFlowType; icon?: string; color?: string};

export async function listCategories(type?: MoneyFlowType) {
  const res = await api.get<Category[]>("/categories", { params: { type } });
  return res.data;
}

export async function createCategory(payload: { name: string; type: MoneyFlowType; icon?: string; color?: string }) {
  const res = await api.post<Category>("/categories", payload);
  return res.data;
}

