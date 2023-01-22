# --- !Ups

CREATE TABLE PLAY.SERVICE(
                               ID BIGINT NOT NULL PRIMARY KEY ,
                               NAME VARCHAR(255)
);

CREATE TABLE PLAY.PROPERTY (
                                 ID BIGINT NOT NULL PRIMARY KEY ,
                                 NAME VARCHAR(255),
                                 LOCATION VARCHAR(255)
);

CREATE TABLE PLAY.SERVICE_PROPERTY(
                                        ID BIGINT NOT NULL PRIMARY KEY ,
                                        PROPERTY_ID BIGINT NOT NULL,
                                        SERVICE_ID BIGINT NOT NULL
);


INSERT INTO PLAY.SERVICE(ID, NAME) VALUES
                                         (1,'cleaning'),
                                         (2,'laundry'),
                                         (3, 'cooking');

INSERT INTO PLAY.PROPERTY(ID, NAME, LOCATION) VALUES
                                                    (1, 'mall-egypt', 'Giza'),
                                                    (2, 'cairo-festival', 'Cairo');

INSERT INTO PLAY.SERVICE_PROPERTY(ID, PROPERTY_ID, SERVICE_ID) VALUES
                                                                     (1, 1, 1),
                                                                     (2, 1, 2),
                                                                     (3, 1, 3),
                                                                     (4, 2, 1),
                                                                     (5, 2, 2);


# --- !Downs

DROP TABLE PLAY.SERVICE_PROPERTY;
DROP TABLE PLAY.SERVICE;
DROP TABLE PLAY.PROPERTY;