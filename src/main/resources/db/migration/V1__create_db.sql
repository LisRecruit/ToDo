CREATE TABLE Notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    content TEXT,
    CONSTRAINT check_title_or_content CHECK (title IS NOT NULL OR content IS NOT NULL)
);


