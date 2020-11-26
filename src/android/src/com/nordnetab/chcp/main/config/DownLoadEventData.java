package com.nordnetab.chcp.main.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nordnetab.chcp.main.model.UpdateTime;

import android.text.TextUtils;

/**
 * Created by Nikolay Demyankov on 22.07.15.
 * <p/>
 * Model for content configuration.
 * Holds information about current/new release, when to perform the update installation and so on.
 * Basically, it is a part of the chcp.json file, just moved to separate class for convenience.
 */
public class DownLoadEventData {

    // JSON keys to parse chcp.json
    private static class JsonKeys {
        public static final String DOWNLOAD_PROGRESS = "downloadProgress";
        public static final String DOWNLOAD_TOTAL= "downloadTotal";
    }

    /**
     * Create instance of the class from JSON node.
     *
     * @param node JSON node with data from chcp.json file
     * @return content configuration object
     * @see JsonNode
     */
    static DownLoadEventData fromJson(JsonNode node) {
        DownLoadEventData data = new DownLoadEventData();
        try {
            if (node.has(JsonKeys.DOWNLOAD_PROGRESS)) {
                data.setDownloadProgress(node.get(JsonKeys.DOWNLOAD_PROGRESS).asInt());
            }

            if (node.has(JsonKeys.DOWNLOAD_TOTAL)) {
                data.setDownloadTotal(node.get(JsonKeys.DOWNLOAD_TOTAL).asInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

 
    private int downloadProgress;
    private int downloadTotal;
    private JsonNode jsonNode;
    private String jsonString;

    public DownLoadEventData() {
    }

    /**
     * Getter for the download progress.
     * download progress
     *
     * @return download progress
     */
    public int getDownloadProgress() {
        return this.downloadProgress;
    }
     /**
     * Getter for the download total.
     * download total
     *
     * @return download total
     */
    public int getDownloadTotal() {
        return this.downloadTotal;
    }
    /**
     * Setter for the download total.
     * download progress
     *
     * @return download total
     */
    public int setDownloadTotal(int downloadTotal) {
        return this.downloadTotal = downloadTotal;
    }
    /**
     * Setter for the download progress.
     * download progress
     *
     * @return download total
     */
    public void setDownloadProgress(int downloadProgress) {
        this.downloadProgress = downloadProgress;
    }
    /**
     * Convert object into JSON node instance.
     *
     * @return JSON node
     * @see JsonNode
     */
    public JsonNode toJson() {
        if (jsonNode == null) {
            jsonNode = generateJson();
        }

        return jsonNode;
    }
    /**
     * Generates JSON string from class instance.
     *
     * @return JSON formatted string
     */
    @Override
    public String toString() {
        if (TextUtils.isEmpty(jsonString)) {
            jsonString = toJson().toString();
        }

        return jsonString;
    }
    // region Private API

    
    private JsonNode generateJson() {
        JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

        ObjectNode node = nodeFactory.objectNode();
        node.set(JsonKeys.DOWNLOAD_PROGRESS, nodeFactory.numberNode(downloadProgress));
        node.set(JsonKeys.DOWNLOAD_TOTAL, nodeFactory.numberNode(downloadTotal));

        return node;
    }

    // endregion
}
