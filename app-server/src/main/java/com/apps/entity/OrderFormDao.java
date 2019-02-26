package com.apps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 訂單資訊
 *
 * @author SimonYang
 * @date 2019/2/10
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDER_FORM")
@Component
public class OrderFormDao extends BaseDao {

}
