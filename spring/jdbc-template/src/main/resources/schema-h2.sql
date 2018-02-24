DROP TABLE USER IF EXISTS;
CREATE TABLE IF NOT EXISTS USER (
  id         INT,
  first_name VARCHAR(50),
  last_name  VARCHAR(50),
  email      VARCHAR(50),
  gender     VARCHAR(10)
);