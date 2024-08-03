CREATE TABLE IF NOT EXISTS tb_exchange_rate (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    valor DECIMAL(10,2),
    create_at TIMESTAMP
);

INSERT INTO tb_exchange_rate (type, valor, create_at) VALUES ('USD', 3.71, now());
INSERT INTO tb_exchange_rate (type, valor, create_at) VALUES ('EUR', 4.01, now());