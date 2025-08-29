import { useEffect, useState } from "react"
import { listTransactions, type Transaction } from "@/api/transactions"
import { DataTable } from "@/components/data-table/data-table"
import { transactionColumns } from "./transactions/columns"

export default function Dashboard() {
  const [tx, setTx] = useState<Transaction[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    (async () => {
      try {
        const data = await listTransactions()
        setTx(data)
      } catch (e) {
        console.error(e)
        alert("Failed to load transactions. Is the backend running? Is X-User-Id set?")
      } finally {
        setLoading(false)
      }
    })()
  }, [])

  return (
      <section>
        <div className="bg-stone-950 p-8">
            <h1 className="text-2xl font-semibold">Transactions</h1>
            {loading ? (
              <div className="text-sm text-muted-foreground">Loading…</div>
            ) : (
              <DataTable columns={transactionColumns} data={tx} />
            )}
        </div>
      </section>

  )
}