-- event table
-- columns:  event_type, value, time
-- problem:  for events that happened more than once, return the difference of time between latest and 2nd-latest event 

		-- write your code in SQLite 3.8.6
		select e1.event_type, 
		       e1.value - (select value
		                     from events
		                    where event_type = e1.event_type
		                      and time = 
		                          (select max(time)
		                             from events
		                            where event_type=e1.event_type
		                              and time < e1.time)
		                   ) as value
		  from events e1
		 where e1.event_type in (
		    select event_type
		      from events
		     group by event_type
		     having count(*) > 1
		   )
		   and e1.time = (
		    select max(time)
		      from events
		     where event_type = e1.event_type
		     group by event_type
		   )   
		 order by event_type

		-- alternate - doesn't work yet
		select e1.event_type, e1.value-e2.value as value
		  from events e1 left join events e2
		    on e1.event_type = e2.event_type
		   and e2.time < e1.time
		   and e2.time = (
		          select max(time) 
		            from events
		           where event_type=e1.event_type
		             and time < e1.time
		   )
		 group by e1.event_type
		 having count(e1.event_type) > 1
		 order by e1.event_type
