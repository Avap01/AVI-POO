package br.com.mariojp.model;

public class Post {

    private static Integer count = 1;
    private Integer oid;
    private String title;
    private String content;
    private String template;

    public Post(String title, String content, String template) {
        this.oid = count++;
        this.title = title;
        this.content = content;
        this.template = template!=null?template:"simples";
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Integer getOid() {
        return oid;
    }


}
