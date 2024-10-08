MERGE INTO PEER_STATUS
(
    CHANNEL_TYPE,
    PEER,
    PEER_STATUS,
    CAUSE,
    ADDRESS,
    PORT,
    TIME,
    _INSTANCE,
    _TIMESTAMP
)
VALUES
(
    :#${body['ChannelType']},
    :#${body['Peer']},
    :#${body['PeerStatus']},
    :#${body['Cause']},
    :#${body['Address']},
    :#${body['Port']},
    :#${body['Time']},
    :#${headers['AMI_INSTANCE']},
    PARSEDATETIME(:#${headers['AMI_TIMESTAMP']}, 'yyyy-MM-dd''T''HH:mm:ss.SSSZ')
);

MERGE INTO PEER_STATUS_LOG
(
    CHANNEL_TYPE,
    PEER,
    PEER_STATUS,
    CAUSE,
    ADDRESS,
    PORT,
    TIME,
    _INSTANCE,
    _TIMESTAMP
)
VALUES
(
    :#${body['ChannelType']},
    :#${body['Peer']},
    :#${body['PeerStatus']},
    :#${body['Cause']},
    (CASE WHEN :#${body['Address']} IS NULL THEN '' ELSE :#${body['Address']} END),
    :#${body['Port']},
    :#${body['Time']},
    :#${headers['AMI_INSTANCE']},
    PARSEDATETIME(:#${headers['AMI_TIMESTAMP']}, 'yyyy-MM-dd''T''HH:mm:ss.SSSZ')
);