# 底層物件宣告

## 類別介紹

### BaseDao 基礎Dao
> 宣告所有的資料表都必須記錄的資訊
- 啟用 boolean action = true
- 刪除 boolean alive = true
- 建立日期 LocalDateTime createDate
- 建立人 createUser
- 建立IP createAddress
- 修改日期 LocalDateTime updateDate
- 修改人 updateUser
- 修改IP updateAddress
### BaseService 基礎Service
> 宣告所有服務都必須提供的方法
- 新增
- 刪除(保留紀錄)
- 刪除(不保留紀錄)
- 取得清單
- 以 ID 查詢紀錄
- 啟用/停用
### BaseServiceImpl 基礎實作
> 以多型實作基礎方法

## 共用工具

### BaseMsg 共用名詞
> 宣告系統中常用名詞
### DateUtility 時間工具
> 時間處理工具

