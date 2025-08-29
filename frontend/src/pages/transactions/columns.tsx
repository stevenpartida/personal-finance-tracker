import { type ColumnDef } from "@tanstack/react-table"
import type { Transaction } from "@/api/transactions"
import { format } from "date-fns"
import { Badge } from "@/components/ui/badge"

export const transactionColumns: ColumnDef<Transaction>[] = [
  {
    accessorKey: "name",
    header: "Name",
  },
  {
    accessorKey: "category",
    header: "Category",
    cell: ({ row }) => {
      const cat = row.original.category
      if (!cat) return "-"
      return (
        <Badge
          className="flex items-center gap-2 text-black px-2 py-1"
          style={{ backgroundColor: cat.color || "#999"}}
        >
          <span>{cat.icon}</span>
          <span>{cat.name}</span>
        </Badge>
      )
    },
  },
  {
    accessorKey: "type",
    header: "Type",
    cell: ({ row }) => {
      const t: "INCOME" | "EXPENSE" = row.original.type
      const formatted = t.charAt(0) + t.slice(1).toLowerCase()
      return <Badge variant="outline" className="text-white px-2 py-1">{formatted}</Badge>
    },
  },
  {
    accessorKey: "amount",
    header: "Amount",
    cell: ({ row }) => {
      const amt = Number(row.original.amount)
      return `$${amt.toFixed(2)}`
    },
  },
  {
    accessorKey: "occurredOn",
    header: "Date",
    cell: ({ row }) =>
      format(new Date(row.original.occurredOn), "MMM d"),
  },
]