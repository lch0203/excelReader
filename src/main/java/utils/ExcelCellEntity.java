package utils;

/**
 * Created by lch on 2018/2/28.
 */
public class ExcelCellEntity {
    private Integer row;
    private Integer col;
    private String value;
    private String fieldName;

   public ExcelCellEntity(Integer row,Integer col){
       this.row = row;
       this.col = col;
   }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
