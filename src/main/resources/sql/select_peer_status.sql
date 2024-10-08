SELECT PEER_STATUS.CHANNEL_TYPE,
       PEER_STATUS.PEER,
       PEER_STATUS.PEER_STATUS,
       PEER_STATUS.CAUSE,
       PEER_STATUS.ADDRESS,
       PEER_STATUS.PORT,
       PEER_STATUS.TIME,
       PEER_STATUS._INSTANCE,
       PEER_STATUS._TIMESTAMP,
       DEVICE_STATE.STATE,
       DEVICE_STATE._TIMESTAMP AS _STATE_TIMESTAMP
FROM PEER_STATUS
LEFT JOIN DEVICE_STATE
ON PEER_STATUS.PEER = DEVICE_STATE.DEVICE
ORDER BY _INSTANCE, LENGTH(PEER), PEER;