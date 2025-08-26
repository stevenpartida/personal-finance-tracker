INSERT INTO categories (id, user_id, name, type, icon, color)
VALUES
  -- Expenses
  (gen_random_uuid(), NULL, 'Auto & Transport',     'EXPENSE', '🚘', '#9BBEED'),
  (gen_random_uuid(), NULL, 'Bills & Utilities',    'EXPENSE', '💡', '#B4D9EF'),
  (gen_random_uuid(), NULL, 'Business',             'EXPENSE', '🏢', '#BEE5B0'),
  (gen_random_uuid(), NULL, 'Cash & Checks',        'EXPENSE', '💵', '#20c997'),
  (gen_random_uuid(), NULL, 'Charitable Donations', 'EXPENSE', '🌎', '#ADD0B3'),
  (gen_random_uuid(), NULL, 'Education',            'EXPENSE', '🎓', '#FBBB62'),
  (gen_random_uuid(), NULL, 'Dining & Drinks',      'EXPENSE', '🍽️', '#AF8FE9'),
  (gen_random_uuid(), NULL, 'Entertainment & Rec.', 'EXPENSE', '🎮', '#FBF38A'),
  (gen_random_uuid(), NULL, 'Family Care',          'EXPENSE', '❤️', '#F6B2D7'),
  (gen_random_uuid(), NULL, 'Fees',                 'EXPENSE', '💳', '#dc3545'),
  (gen_random_uuid(), NULL, 'Gifts',                'EXPENSE', '🎁', '#EBCCFF'),
  (gen_random_uuid(), NULL, 'Groceries',            'EXPENSE', '🛒', '#BEE5B0'),
  (gen_random_uuid(), NULL, 'Health & Wellness',    'EXPENSE', '🏋', '#B4D9EF'),
  (gen_random_uuid(), NULL, 'Legal',                'EXPENSE', '⚖️', '#EA8183'),
  (gen_random_uuid(), NULL, 'Loan Payment',         'EXPENSE', '💳', '#C9D6E8'),
  (gen_random_uuid(), NULL, 'Medical',              'EXPENSE', '🩺', '#93CAED'),
  (gen_random_uuid(), NULL, 'Personal Care',        'EXPENSE', '🧴', '#F0B6D5'),
  (gen_random_uuid(), NULL, 'Pets',                 'EXPENSE', '🐾', '#E5ECF8'),
  (gen_random_uuid(), NULL, 'Shopping',             'EXPENSE', '🛍️', '#F8F1AE'),
  (gen_random_uuid(), NULL, 'Software & Tech',      'EXPENSE', '💻', '#89CFF0'),
  (gen_random_uuid(), NULL, 'Taxes',                'EXPENSE', '📑', '#CCCCC4'),
  (gen_random_uuid(), NULL, 'Travel & Vacation',    'EXPENSE', '✈️', '#E5788F'),

  -- Earnings
  (gen_random_uuid(), NULL, 'Income',               'INCOME',  '💰', '#ADD0B3'),
  (gen_random_uuid(), NULL, 'Investments',          'INCOME',  '📈', '#9ECB91')
ON CONFLICT DO NOTHING;