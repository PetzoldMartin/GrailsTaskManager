<g:if test="${kind == 'heading'}">
	<th>${message(code: 'task.name.id', default: 'ID')}</th>
	<th>${message(code: 'task.name.label', default: 'Name')}</th>
	<th>${message(code: 'task.compound.label', default: 'Comp')}</th>
	<th>${message(code: 'task.description.label', default: 'Beschreibung')}</th>
	<th>${message(code: 'task.description.parentId', default: 'Supertask')}</th>
	<th>${message(code: 'task.timeBudgetUsed.label', default: 'Gebucht')}</th>
	<th>${message(code: 'task.timeBudgetPlan.label', default: 'Geplant')}</th>
	<th>${message(code: 'task.start.label', default: 'Start')}</th>
</g:if>
<g:else>
	<g:set var="key" value="${kind == 'edit' ? 'in' : 'cr' }" />
	<g:set var="index" scope="request" value="${kind == 'edit' ? taskInfo.id : (index ? ++index : 1) }" />
	<td>
		<g:if test="${kind == 'edit'}">
			<g:hiddenField name="${key}.${index}.id" value="${taskInfo?.id}"/>
			<g:hiddenField name="${key}.${index}.version" value="${taskInfo?.version}"/>
			<g:link controller="task" action="show" id="${taskInfo?.id}">
					${taskInfo?.id}
			</g:link>
		</g:if>
	</td>
	<td>
		<g:field name="${key}.${index}.name" type="text" size="12" value=" ${taskInfo? taskInfo.name : ''}" />
	</td>
	<td>
		<g:if test="${kind == 'edit'}">
			<g:field type="checkBox" name="${key}.${index}.compound" checked="${taskInfo?.compound}" disabled="true" />
		</g:if>
		<g:else>
			<g:checkBox name="${key}.${index}.compound" checked="${taskInfo?.compound}" />
		</g:else>
	</td>
	<td>
		<g:textArea class="taskedit" name="${key}.${index}.description" rows="2" cols="30" value="${taskInfo?.description}" />
	</td>
	<td>
		<g:select name="${key}.${index}.parent" from="${compoundTasks}" optionKey="id" 
				optionValue="name" value="${taskInfo?.parentId}" 
				noSelection="${['-1':'Top Level Task']}" />
	</td>
	<td>${taskInfo?.timeBudgetUsed}</td>
	<td>
		<g:field name="${key}.${index}.timeBudgetPlan" type="number"
			 min="0" max="100000"
			 size="6" value=" ${taskInfo?.timeBudgetPlan ? taskInfo?.timeBudgetPlan : 0}" />
	</td>
	<td>
		<g:field name="${key}.${index}.start" type="text" 
				value="${taskInfo?.start?.format('dd.MM.yyyy')}" size="10" 
				pattern="${~/\d\d\.\d\d\.\d\d\d\d/}"
				readonly="${taskInfo?.start && taskInfo?.timeBudgetUsed > 0 ? 'true':'false'}"/>
	</td>
</g:else>