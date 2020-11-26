package com.nordnetab.chcp.main.events;

import com.nordnetab.chcp.main.config.ApplicationConfig;
import com.nordnetab.chcp.main.config.DownLoadEventData;
import com.nordnetab.chcp.main.model.ChcpError;
import org.json.JSONObject;

/**
 * Created by Nikolay Demyankov on 25.08.15.
 * <p/>
 * Event is send when some error happened during the update download.
 */
public class UpdateDownloadProgressEvent extends WorkerEvent {

    public static final String EVENT_NAME = "chcp_updateLoadProgress";

    /**
     * Class constructor.
     *
     * @param progress  download progress
     * @param total download total
     */
    public UpdateDownloadProgressEvent(DownLoadEventData downLoadEventData) {
        super(EVENT_NAME, downLoadEventData);
    }
}
