package com.example.javautil.utils;

public class Marker {
    private String module = "default";
    private String category = "";
    private String subCategory = "";
    private String clazzName;

    public Marker() {
    }

    public Marker(String module) {
        this.module = module;
    }

    public Marker(String module, String catalog) {
        this.module = module;
        this.category = catalog;
    }

    public Marker(String module, String category, String subCategory) {
        this.module = module;
        this.category = category;
        this.subCategory = subCategory;
    }

    public String getModule() {
        return this.module;
    }

    public String getCategory() {
        return this.category;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public String getClazzName() {
        return this.clazzName;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Marker)) {
            return false;
        } else {
            Marker other = (Marker) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59:
                {
                    Object this$module = this.getModule();
                    Object other$module = other.getModule();
                    if (this$module == null) {
                        if (other$module == null) {
                            break label59;
                        }
                    } else if (this$module.equals(other$module)) {
                        break label59;
                    }

                    return false;
                }

                Object this$category = this.getCategory();
                Object other$category = other.getCategory();
                if (this$category == null) {
                    if (other$category != null) {
                        return false;
                    }
                } else if (!this$category.equals(other$category)) {
                    return false;
                }

                Object this$subCategory = this.getSubCategory();
                Object other$subCategory = other.getSubCategory();
                if (this$subCategory == null) {
                    if (other$subCategory != null) {
                        return false;
                    }
                } else if (!this$subCategory.equals(other$subCategory)) {
                    return false;
                }

                Object this$clazzName = this.getClazzName();
                Object other$clazzName = other.getClazzName();
                if (this$clazzName == null) {
                    if (other$clazzName != null) {
                        return false;
                    }
                } else if (!this$clazzName.equals(other$clazzName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Marker;
    }


    public String toString() {
        return "Marker(module=" + this.getModule() + ", category=" + this.getCategory() + ", subCategory=" + this.getSubCategory() + ", clazzName=" + this.getClazzName() + ")";
    }
}
