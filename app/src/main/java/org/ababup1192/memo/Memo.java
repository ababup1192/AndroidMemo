package org.ababup1192.memo;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "Memo")
public class Memo {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String contents = "";
    @DatabaseField
    private Date updateTime;

    public Memo() {
    }

    public Memo(Integer id, String contents, Date updateTime) {
        this.id = id;
        this.contents = contents;
        this.updateTime = updateTime;
    }

    public Memo(String contents) {
        this.contents = contents;
        this.updateTime = new Date();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        if (contents.length() >= 10) {
            return contents.trim().substring(0, 10);
        } else {
            return contents.trim();
        }
    }

    public String getContents() {
        return contents;
    }

    public Date getDate() {
        return updateTime;
    }

}
