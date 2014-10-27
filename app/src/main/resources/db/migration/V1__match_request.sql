CREATE TABLE match_request (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  uuid         VARCHAR(255) NOT NULL,
  requester_id VARCHAR(255) NOT NULL,

  CONSTRAINT unique_uuid UNIQUE (uuid)
);
