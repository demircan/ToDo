package com.iyzico.dao.impl;

import org.springframework.stereotype.Component;
import com.iyzico.dao.TaskDaoI;
import com.iyzico.domain.Task;

@Component
public class TaskDaoImpl extends CommonDaoImpl<Task, Long> implements TaskDaoI {

}
