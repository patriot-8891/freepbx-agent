DELETE
FROM PEER_STATUS
WHERE (CURRENT_TIMESTAMP - (INTERVAL '1' SECOND * CAST(:#${headers['SQL_DELETE_INTERVAL']} AS INTEGER))) > _TIMESTAMP;

DELETE
FROM PEER_STATUS_LOG
WHERE (CURRENT_TIMESTAMP - (INTERVAL '1' SECOND * CAST(:#${headers['SQL_DELETE_INTERVAL']} AS INTEGER))) > _TIMESTAMP;

DELETE
FROM INVALID_EVENT_LOG
WHERE (CURRENT_TIMESTAMP - (INTERVAL '1' SECOND * CAST(:#${headers['SQL_DELETE_INTERVAL']} AS INTEGER))) > _TIMESTAMP;

DELETE
FROM REGISTRY
WHERE (CURRENT_TIMESTAMP - (INTERVAL '1' SECOND * CAST(:#${headers['SQL_DELETE_INTERVAL']} AS INTEGER))) > _TIMESTAMP;


