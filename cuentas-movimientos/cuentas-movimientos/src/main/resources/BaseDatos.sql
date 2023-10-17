CREATE TABLE CUENTA (
    ID BIGINT  AUTO_INCREMENT PRIMARY KEY,
    NUMERO_CUENTA VARCHAR(20) UNIQUE NOT NULL,
    TIPO_CUENTA VARCHAR(15) NOT NULL,
    SALDO_INICIAL DECIMAL(18, 2) NOT NULL,
    ESTADO VARCHAR(15) NOT NULL,
    ID_CLIENTE BIGINT NOT NULL
);


CREATE TABLE MOVIMIENTOS (
    ID BIGINT  AUTO_INCREMENT PRIMARY KEY,
    FECHA TIMESTAMP NOT NULL,
    TIPO_MOVIMIENTO VARCHAR(50) NOT NULL,
    VALOR DECIMAL(18, 2) NOT NULL,
    SALDO DECIMAL(18, 2) NOT NULL,
    ID_CUENTA BIGINT NOT NULL, 
    FOREIGN KEY (ID_CUENTA) REFERENCES CUENTA(ID)
);


INSERT INTO CUENTA ( NUMERO_CUENTA, TIPO_CUENTA, SALDO_INICIAL, ESTADO, ID_CLIENTE)
VALUES ('478758', 'Ahorro', 2000, 'True',  1);

INSERT INTO CUENTA ( NUMERO_CUENTA, TIPO_CUENTA, SALDO_INICIAL, ESTADO, ID_CLIENTE)
VALUES ('225487', 'Corriente', 100, 'True',  2);

INSERT INTO CUENTA ( NUMERO_CUENTA, TIPO_CUENTA, SALDO_INICIAL, ESTADO, ID_CLIENTE)
VALUES ( '495878', 'Ahorros', 0, 'True',  3);

INSERT INTO CUENTA ( NUMERO_CUENTA, TIPO_CUENTA, SALDO_INICIAL, ESTADO, ID_CLIENTE)
VALUES ( '496825', 'Ahorros', 540, 'True',  2);








