CREATE DATABASE IF NOT EXISTS formulario_db;
USE formulario_db;

CREATE TABLE formularios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(25),
    mensagem TEXT NOT NULL,
    arquivo_nome VARCHAR(255),
    arquivo_tipo VARCHAR(100), 
    arquivo_caminho TEXT,
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_email (email),
    INDEX idx_data (data_envio)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO formularios (nome, email, telefone, mensagem, arquivo_nome, arquivo_tipo, arquivo_caminho)
VALUES 
('João Silva', 'joao@gmail.com', '(11) 98888-1234', 'Gostaria de orçamento para impressão 3D de peça automotiva.', 'peca1.pdf', 'application/pdf', '/uploads/peca1.pdf'),
('Maria Oliveira', 'maria.oliveira@hotmail.com', '(21) 97777-2345', 'Preciso de orçamento para protótipo de design.', 'design_maria.png', 'image/png', '/uploads/design_maria.png'),
('Carlos Souza', 'carlos_souza@yahoo.com', '(31) 96666-3456', 'Quero um orçamento urgente para um projeto em 3D.', NULL, NULL, NULL),
('Ana Paula', 'ana.paula@empresa.com', '(47) 95555-4567', 'Estou enviando meu projeto em anexo para análise.', 'modelo_ap.jpg', 'image/jpeg', '/uploads/modelo_ap.jpg');
