CONNECT 'jdbc:derby:bankSandboxDB;create=true';

CREATE TABLE Account(
    id char(36) NOT NULL PRIMARY KEY,
    userId char(36) NOT NULL,
    initialBalance decimal(19, 4) NOT NULL,
    createdDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Tranzaction(
    id char(36) NOT NULL PRIMARY KEY,
    source char(36) NOT NULL,
    target char(36) NOT NULL,
    value decimal(19, 4) NOT NULL,
    createdDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX TranzactionSourceIndex ON Tranzaction(source);
CREATE INDEX TranzactionTargetIndex ON Tranzaction(target);

INSERT INTO Account(id, userId, initialBalance) VALUES(
    '30e51133-1170-46dd-a19c-2a64a8fa9fe7',
    '11319b07-8455-4e7d-8349-770759f7a21a',
    200.4567
);
INSERT INTO Account(id, userId, initialBalance) VALUES(
    'dd7955ea-fcf6-4b38-b728-cafd7161ed5c',
    '5d1b4bb3-3ab7-448a-8dab-e6b9eabeaa30',
    300.0
);
