function processAjax(data, f) {
  if (data && data.code == 1) {
    showMsgError(data.err);
  } else {
    if (f) {
      f(data);      
    }
  }
}
function doAjax(url, f) {
  $.getJSON(url, function(data) {
    processAjax(data, f)
  });
}
function doAjaxPost(url, f, fe) {
  $.post(url, function(data) {
    processAjax(data, f)
  }).error(function(jqXHR, textStatus, errorThrown) {
    if (jqXHR.status == 400) {
      var obj = jQuery.parseJSON(jqXHR.responseText)
      if (fe) {
        fe(obj);
      } else {
        alert(obj[0]);        
      }
    } else {
      alert(textStatus + " " + jqXHR.status + ": " + errorThrown);
    }
  });
}

function showMsgInfo(msg)
{
  $('#dialog-message-type').removeClass('ui-state-error').addClass('ui-state-highlight');
  showMsgDialog('Info', msg);
}

function showMsgError(msg)
{
  $('#dialog-message-type').removeClass('ui-state-highlight').addClass('ui-state-error');
  showMsgDialog('Error', msg);
}

function showMsgDialog(title, msg)
{
  $('#dialog-message .msg').text(msg);
  $('#dialog-message').dialog({
    title: title,
    modal: true,
    buttons: {
      Ok: function() {
        $(this).dialog( "close" );
        location.reload();
      }
    }
  });
}
