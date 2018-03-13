package com.mycom.entity;

import java.util.ArrayList;
import java.util.List;

public class StorageFileExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public StorageFileExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andStorageShareIsNull() {
            addCriterion("STORAGE_SHARE is null");
            return (Criteria) this;
        }

        public Criteria andStorageShareIsNotNull() {
            addCriterion("STORAGE_SHARE is not null");
            return (Criteria) this;
        }

        public Criteria andStorageShareEqualTo(String value) {
            addCriterion("STORAGE_SHARE =", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareNotEqualTo(String value) {
            addCriterion("STORAGE_SHARE <>", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareGreaterThan(String value) {
            addCriterion("STORAGE_SHARE >", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_SHARE >=", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareLessThan(String value) {
            addCriterion("STORAGE_SHARE <", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_SHARE <=", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareLike(String value) {
            addCriterion("STORAGE_SHARE like", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareNotLike(String value) {
            addCriterion("STORAGE_SHARE not like", value, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareIn(List<String> values) {
            addCriterion("STORAGE_SHARE in", values, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareNotIn(List<String> values) {
            addCriterion("STORAGE_SHARE not in", values, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareBetween(String value1, String value2) {
            addCriterion("STORAGE_SHARE between", value1, value2, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageShareNotBetween(String value1, String value2) {
            addCriterion("STORAGE_SHARE not between", value1, value2, "storageShare");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirIsNull() {
            addCriterion("STORAGE_PARENT_DIR is null");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirIsNotNull() {
            addCriterion("STORAGE_PARENT_DIR is not null");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirEqualTo(String value) {
            addCriterion("STORAGE_PARENT_DIR =", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirNotEqualTo(String value) {
            addCriterion("STORAGE_PARENT_DIR <>", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirGreaterThan(String value) {
            addCriterion("STORAGE_PARENT_DIR >", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_PARENT_DIR >=", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirLessThan(String value) {
            addCriterion("STORAGE_PARENT_DIR <", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_PARENT_DIR <=", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirLike(String value) {
            addCriterion("STORAGE_PARENT_DIR like", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirNotLike(String value) {
            addCriterion("STORAGE_PARENT_DIR not like", value, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirIn(List<String> values) {
            addCriterion("STORAGE_PARENT_DIR in", values, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirNotIn(List<String> values) {
            addCriterion("STORAGE_PARENT_DIR not in", values, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirBetween(String value1, String value2) {
            addCriterion("STORAGE_PARENT_DIR between", value1, value2, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageParentDirNotBetween(String value1, String value2) {
            addCriterion("STORAGE_PARENT_DIR not between", value1, value2, "storageParentDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirIsNull() {
            addCriterion("STORAGE_CHILD_DIR is null");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirIsNotNull() {
            addCriterion("STORAGE_CHILD_DIR is not null");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirEqualTo(String value) {
            addCriterion("STORAGE_CHILD_DIR =", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirNotEqualTo(String value) {
            addCriterion("STORAGE_CHILD_DIR <>", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirGreaterThan(String value) {
            addCriterion("STORAGE_CHILD_DIR >", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirGreaterThanOrEqualTo(String value) {
            addCriterion("STORAGE_CHILD_DIR >=", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirLessThan(String value) {
            addCriterion("STORAGE_CHILD_DIR <", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirLessThanOrEqualTo(String value) {
            addCriterion("STORAGE_CHILD_DIR <=", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirLike(String value) {
            addCriterion("STORAGE_CHILD_DIR like", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirNotLike(String value) {
            addCriterion("STORAGE_CHILD_DIR not like", value, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirIn(List<String> values) {
            addCriterion("STORAGE_CHILD_DIR in", values, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirNotIn(List<String> values) {
            addCriterion("STORAGE_CHILD_DIR not in", values, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirBetween(String value1, String value2) {
            addCriterion("STORAGE_CHILD_DIR between", value1, value2, "storageChildDir");
            return (Criteria) this;
        }

        public Criteria andStorageChildDirNotBetween(String value1, String value2) {
            addCriterion("STORAGE_CHILD_DIR not between", value1, value2, "storageChildDir");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table storage_file
     *
     * @mbg.generated do_not_delete_during_merge Tue Mar 13 10:15:32 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table storage_file
     *
     * @mbg.generated Tue Mar 13 10:15:32 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}