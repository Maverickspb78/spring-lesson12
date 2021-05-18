DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) UNIQUE  NOT NULL,
    password VARCHAR(255) UNIQUE  NOT NULL
);

INSERT INTO users (login, password) VALUES
('admin', '$2a$10$yhoAXBKrkLsF.uIHQbK8iu9XfhopT6QVH7oYJtVmawReAj7JG.586'),
('user', '$2a$10$dQ1ysAzF39NufYZDvexKU.t9Z32/AgluHgTi6DdgUWk4cKV3m.que'),
('userg', '$2a$10$fxxxVVi.hTxVxOnlZ3DeaeB4fQDlAMuaLzW0udydgNfzqow.vaAtG');