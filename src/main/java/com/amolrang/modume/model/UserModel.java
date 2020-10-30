package com.amolrang.modume.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class UserModel extends User_JPA{
	private List<String> sns= new ArrayList<String>();
}
