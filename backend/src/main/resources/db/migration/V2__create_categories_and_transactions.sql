CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    name TEXT NOT NULL,
    type TEXT NOT NULL CHECK (type IN ('EXPENSE', 'INCOME')),
    icon TEXT,
    color TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE UNIQUE INDEX IF NOT EXISTS uq_cat_user_name_type
  ON categories (user_id, lower(name), type);

CREATE UNIQUE INDEX IF NOT EXISTS uq_cat_global_name_type
  ON categories (lower(name), type) WHERE user_id is NULL;

-- Transaction Table
CREATE TABLE IF NOT EXISTS transactions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    category_id UUID REFERENCES categories(id) ON DELETE SET NULL,
    amount NUMERIC(14,2) NOT NULL CHECK (amount >= 0),
    type TEXT NOT NULL CHECK (type in ('EXPENSE', 'INCOME')),
    occurred_on DATE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_tx_user_date
  ON transactions (user_id, occurred_on);

CREATE INDEX IF NOT EXISTS idx_tx_user_cat_date
  ON transactions (user_id, category_id, occurred_on);