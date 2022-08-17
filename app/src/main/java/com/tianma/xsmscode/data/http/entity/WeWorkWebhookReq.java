package com.tianma.xsmscode.data.http.entity;

import java.util.List;

public class WeWorkWebhookReq {
    private String msgtype;
    private Text text;
    private Markdown markdown;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }

    public static class Text {
        public Text(String content, List<String> mentioned_list, List<String> mentioned_mobile_list) {
            this.content = content;
            this.mentioned_list = mentioned_list;
            this.mentioned_mobile_list = mentioned_mobile_list;
        }

        private String content;
        private List<String> mentioned_list;
        private List<String> mentioned_mobile_list;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMentioned_list() {
            return mentioned_list;
        }

        public void setMentioned_list(List<String> mentioned_list) {
            this.mentioned_list = mentioned_list;
        }

        public List<String> getMentioned_mobile_list() {
            return mentioned_mobile_list;
        }

        public void setMentioned_mobile_list(List<String> mentioned_mobile_list) {
            this.mentioned_mobile_list = mentioned_mobile_list;
        }
    }

    public static class Markdown {
        public Markdown(String content) {
            this.content = content;
        }

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
