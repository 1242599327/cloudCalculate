package com;

public class CsdnBlog {
    private int id;// ���
    private String title;// ����
    private String date;// ����
    private String category;// ����
    private int view;// �Ķ�����
    private int comments;// ��������
    private String copyright;// �Ƿ�ԭ��

    public CsdnBlog id(int id){
        this.id = id;
        return this;
    }
    public CsdnBlog date(String date){
        this.date = date;
        return this;
    }
    public CsdnBlog title(String title){
        this.title = title;
        return this;
    }
    public CsdnBlog category(String category){
        this.category = category;
        return this;
    }
    public CsdnBlog view(int view){
        this.view = view;
        return this;
    }
    public CsdnBlog comments(int comments){
        this.comments = comments;
        return this;
    }
    public CsdnBlog copyright(String copyright){
        this.copyright = copyright;
        return this;
    }

    @Override
    public String toString() {
        return "CsdnBlog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", view=" + view +
                ", comments=" + comments +
                ", copyright='" + copyright + '\'' +
                '}';
    }

}
