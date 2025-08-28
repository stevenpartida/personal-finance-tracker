import { useEffect, useState } from "react";
import { listCategories } from "../api/categories";
import type { Category } from "../api/categories";
import { listTransactions } from "../api/transactions";
import type { Transaction } from "../api/transactions";

export default function Dashboard() {
  const [cats, setCats] = useState<Category[]>([]);
  const [tx, setTx] = useState<Transaction[]>([]);

  useEffect(() => {
    (async () => {
      try {
        const [c, t] = await Promise.all([listCategories(), listTransactions()]);
        setCats(c);
        setTx(t);
      } catch (e) {
        console.error(e);
        alert("Failed to load data. Is the backend running? Is userId set?");
      }
    })();
  }, []);

  return (
    <div style={{ padding: 24 }}>
      <h1>Dashboard</h1>

      <h2>Categories</h2>
      <ul>
        {cats.map(c => (
          <li key={c.id}>{c.name} ({c.type})</li>
        ))}
      </ul>

      <h2>Transactions</h2>
      <ul>
        {tx.map(t => (
          <li key={t.id}>
            {t.type} • ${t.amount} • {new Date(t.occurredOn).toLocaleString()}
          </li>
        ))}
      </ul>
    </div>
  );
}