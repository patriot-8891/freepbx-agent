MERGE INTO REGISTRY
(
 CHANNEL_TYPE,
 USERNAME,
 DOMAIN,
 STATUS,
 CAUSE,
 _INSTANCE,
 _TIMESTAMP
)
VALUES
(
    :#${body['ChannelType']},
    :#${body['Username']},
    :#${body['Domain']},
    :#${body['Status']},
    (CASE WHEN :#${body['Cause']} IS NULL THEN '' ELSE :#${body['Cause']} END),
    :#${headers['AMI_INSTANCE']},
    PARSEDATETIME(:#${headers['AMI_TIMESTAMP']}, 'yyyy-MM-dd''T''HH:mm:ss.SSSZ')
);
