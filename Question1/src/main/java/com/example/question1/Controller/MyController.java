package com.example.question1.Controller;

import com.example.question1.DTO.ResultDTO;
import com.example.question1.Entity.Data;
import com.example.question1.Entity.Entry;
import com.example.question1.Service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class MyController {

	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping("/entries/{category}")
	public List<ResultDTO> getEntriesHandler(@PathVariable("category") String category){
		
		
		Data d= restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		
		List<Entry> entries= d.getEntries();
	
		List<ResultDTO> list = new ArrayList<>();

		for(Entry en:entries) {
			String category1 = en.getCategory();
			String ctgry[] = category1.toLowerCase(Locale.ROOT).split(" ");


			if(ctgry[0].equals(category))
				list.add(new ResultDTO(en.getApi(), en.getDescription()));
		}

		 
		return list;
		
	}
	@Autowired
	private EntityService entityService;
	@PostMapping("/entries")
	public ResponseEntity<String> saveEntriesHandler(@RequestBody Entry entry) {


		Data d= restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entry> entries= d.getEntries();
		entries.add(entry);
		String response = entityService.saveEntries(entries);

     return  new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
}
