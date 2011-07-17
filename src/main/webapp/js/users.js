function processAjax(data, f) {
  if (data && data.code == 1) {
    showMsgError(data.err);
  } else {
    f(data);
  }
}
function doAjax(url, f) {
  $.getJSON(url, function(data) {
    processAjax(data, f)
  });
}
function disableUser(u)
{
  var url = 'disableuser?login=' + u;
 doAjax(url, function(data) {
    //alert(data.msg);
    location.reload();
 });
}
function enableUser(u)
{
  var url = 'enableuser?login=' + u;
  doAjax(url, function(data) {
    //alert(data.msg);
    location.reload();
  });
}
function resetUserPwd(u)
{
  var url = 'resetpassword?login=' + u;
  doAjax(url, function(data) {
    showMsgInfo(data.msg);
    //location.reload();
  });
}

function newUser(u)
{
  var url = 'adduser?login=' + u;
  doAjax(url, function(data) {
    //alert(data.msg);
    location.reload();
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

$(document).ready(function() {
  $('.adduser').click(function() {
    $('.newuser_dialog .login').val('');
    var o = {
        buttons: {
          'OK': function() {
            var login = $('.newuser_dialog .login').val();
            newUser(login);
            $(this).dialog('close');
          },
          'Cancel': function() {
            $(this).dialog('close');
          }
        }
    }
    $('.newuser_dialog').dialog(o);
  });  
});
