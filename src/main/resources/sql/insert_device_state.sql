MERGE INTO DEVICE_STATE
(
    DEVICE,
    STATE,
    _INSTANCE,
    _TIMESTAMP
)
VALUES
(
    :#${body['Device']},
    :#${body['State']},
    :#${headers['AMI_INSTANCE']},
    PARSEDATETIME(:#${headers['AMI_TIMESTAMP']}, 'yyyy-MM-dd''T''HH:mm:ss.SSSZ')
);
