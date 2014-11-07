<tr>
  	  		<td>
  	  		<g:field name="date" type="text" 
				value="${new Date().clearTime().format('dd.MM.yyyy')}" size="10" 
				pattern="${~/\d\d\.\d\d\.\d\d\d\d/}"
				readonly="${true}"
				/>
  	  		
  	  		</td>
  	  	  <td>
  	  	  <g:field name="amount" type="number"
  	  	  		value="0"
			 min="0" max="100000"
			 size="6"  />
  	  	  </td>
  	  	  <td>
  	  	  <g:field name="start" type="text" 
				value="${new Date().clearTime().format('dd.MM.yyyy')}" size="10" 
				pattern="${~/\d\d\.\d\d\.\d\d\d\d/}"	
				/>
  	  	  </td>
  	  	  <td>
  	  	  <g:field name="end" type="text" 
				value="${new Date().clearTime().format('dd.MM.yyyy')}" size="10" 
				pattern="${~/\d\d\.\d\d\.\d\d\d\d/}"
				/>
  	  	  </td>
  	  </tr>