-- Rename 'type' column to 'role' in users table
ALTER TABLE users
RENAME COLUMN type TO role;