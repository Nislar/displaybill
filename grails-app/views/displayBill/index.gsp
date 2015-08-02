<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Statement</title>
	</head>
	<body>
	<div>
	  <div class="panel panel-primary">
      <div class="panel-heading">Statement covering <g:formatDate date="${bill.startDate}" type="date" style="MEDIUM"/> to <g:formatDate date="${bill.endDate}" type="date" style="MEDIUM"/></div>
        <div class="panel-body">
          <h4><span class="text-danger"><g:formatNumber number="${bill.totalCost}" type="currency" currencyCode="GBP"/></span> due on <g:formatDate date="${bill.dueDate}" type="date" style="MEDIUM"/></h4>
        <div>
      </div>
    </div>

    <div class="panel collapsablePanels">
      <div class="panel-heading" role="tab" id="headingTwo">
        <h4 class="panel-title">
          <span class="badge">${bill.subscriptions.size()}</span> package subscriptions (
          <g:each in="${bill.subscriptions}" var="subscription" status="i">
          <g:if test="${subscription.type == 'tv'}">
            <i class="fa fa-television" title="${subscription.name}"></i>
          </g:if>
          <g:if test="${subscription.type == 'broadband'}">
            <i class="fa fa-wifi" title="${subscription.name}"></i>
          </g:if>
          <g:if test="${subscription.type == 'talk'}">
            <i class="fa fa-phone" title="${subscription.name}"></i>
          </g:if>
          </g:each>
          ) for <g:formatNumber number="${bill.subscriptionTotalCost}" type="currency" currencyCode="GBP"/>
        </h4>

        <div class="clearfix">
          <a role="button" class="pull-right" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
            Show subscriptions
            <i class="fa fa-chevron-right"></i>
          </a>
        </div>
      </div>
      <div id="collapseTwo" class="panel-collapse collapse detailsPanel" role="tabpanel" aria-labelledby="headingTwo">
      <h4>Subscriptions</h4>
        <ul class="list-unstyled">
          <g:each in="${bill.subscriptions}" var="subscription" status="i">
          <li><h5>
            <g:if test="${subscription.type == 'tv'}">
              <i class="fa fa-television"></i>
            </g:if>
            <g:if test="${subscription.type == 'broadband'}">
              <i class="fa fa-wifi"></i>
            </g:if>
            <g:if test="${subscription.type == 'talk'}">
              <i class="fa fa-phone"></i>
            </g:if>
            ${subscription.name} for <g:formatNumber number="${subscription.cost}" type="currency" currencyCode="GBP"/></h5>
          </g:each>
          </li>
        </ul>
      </div>

      <div class="panel-heading" role="tab" id="headingThree">
        <h4 class="panel-title">
          <span class="badge">${bill.rentals.size()}</span> rentals and <span class="badge">${bill.buyAndKeep.size()}</span> buy and keep purchases for <g:formatNumber number="${bill.storeTotalCost}" type="currency" currencyCode="GBP"/>
        </h4>

        <div class="clearfix">
          <a role="button" class="pull-right" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
            Show store transactions
            <i class="fa fa-chevron-right"></i>
          </a>
        </div>
      </div>
      <div id="collapseThree" class="panel-collapse collapse detailsPanel" role="tabpanel" aria-labelledby="headingThree">
        <h4>Rentals</h4>
        <ul class="list-unstyled">
          <g:each in="${bill.rentals}" var="rental" status="i">
            <li><span class="text-info">${rental.title}</span> for <g:formatNumber number="${rental.cost}" type="currency" currencyCode="GBP"/></li>
          </g:each>
        </ul>
        <h4>Buy and keep</h4>
        <ul class="list-unstyled">
          <g:each in="${bill.buyAndKeep}" var="purchase" status="i">
              <li><span class="text-info">${purchase.title}</span> for <g:formatNumber number="${purchase.cost}" type="currency" currencyCode="GBP"/></li>
          </g:each>
        </ul>
      </div>

      <div class="panel-heading" role="tab" id="headingOne">
        <h4 class="panel-title">
          <span class="badge">${bill.calls.size()}</span> calls for <g:formatNumber number="${bill.callTotalCost}" type="currency" currencyCode="GBP"/>
        </h4>

        <div class="clearfix">
          <a role="button" class="pull-right" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
            Show calls
            <i class="fa fa-chevron-right"></i>
          </a>
        </div>
      </div>
      <div id="collapseOne" class="panel-collapse collapse detailsPanel" role="tabpanel" aria-labelledby="headingOne">
      <table class="table table-condensed">
        <tr>
          <th>Called Number</th>
          <th>Duration of call</th>
          <th>Cost of call<th>
        </tr>
        <g:each in="${bill.calls}" var="call" status="i">
          <tr>
            <td>${call.calledNumber}</td>
            <td>${call.duration}</td>
            <td><g:formatNumber number="${call.cost}" type="currency" currencyCode="GBP"/></td>
          </tr>
        </g:each>
        </table>
      </div>
    </div>
	</body>
</html>