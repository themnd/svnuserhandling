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
