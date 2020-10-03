function sayHello()
{
    alert("Hello #BuildForSDG Project...");
}

function validateReg()
{
    if(document.reg.fname.value==="")
    {
        alert("Please provide your First Name!");
        document.reg.fname.focus();
        return false;
    }
    
    if(document.reg.lname.value==="")
    {
        alert("Please provide your Last Name!");
        document.reg.lname.focus();
        return false;
    }
    
    if(document.reg.email.value==="")
    {
        alert("Please provide your email!");
        document.reg.email.focus();
        return false;
    }
    
    if(document.reg.phone.value==="")
    {
        alert("Please provide your Phone number!");
        document.reg.phone.focus();
        return false;
    }
    
    if(document.reg.gender.value==="")
    {
        alert("Please select your gender!");
        document.reg.gender.focus();
        return false;
    }
    
    if(document.reg.dob.value==="")
    {
        alert("Please select your Date Of Birth!");
        document.reg.dob.focus();
        return false;
    }
    
    if(document.reg.country.value==="")
    {
        alert("Please provide your Country!");
        document.reg.country.focus();
        return false;
    }
    
    if(document.reg.address.value==="")
    {
        alert("Please provide your Physical Address!");
        document.reg.address.focus();
        return false;
    }
    
    if(document.reg.pass.value==="")
    {
        alert("Please provide the password of your choice!");
        document.reg.pass.focus();
        return false;
    }
    
    if(document.reg.conpass.value==="")
    {
        alert("Please confirm your password!");
        document.reg.conpass.focus();
        return false;
    }
    
    if(document.reg.conpass.value!==document.reg.pass.value)
    {
        alert("Password does not match");
        document.reg.conpass.focus();
        return false;
    }
    
    return (true);
}

function validateSign()
{
    if(document.log.user.value==="")
    {
        alert("Please provide valid email!");
        document.log.user.focus();
        return false;
    }
    
    if(document.log.pass.value==="")
    {
        alert("Please provide valid password!");
        document.log.pass.focus();
        return false;
    }
    
    return (true);
}

function valiate() {
    if(document.ecode.mycode.value==="") {
        alert("Please Enter The Code!");
        document.ecode.mycode.focus();
        return false;
    }
    if(document.ecode.mycode.value!==document.ecode.vcode.value) {
        alert("Error! Incorrect Code.");
        document.ecode.mycode.focus();
        return false;
    }
    return (true);
}

function rist() {
    if(document.respas.newp.value==="") {
        alert("Please enter new password!");
        document.respas.newp.focus();
        return false;
    }
    if(document.respas.conp.value==="") {
        alert("Please Confirm new password!");
        document.respas.conp.focus();
        return false;
    }
    if(document.respas.newp.value!==document.respas.conp.value) {
        alert("Error! Password does not match!");
        document.respas.conp.focus();
        return false;
    }
    return (true);
}

function sendvl() {
    if(document.senda.recpt.value==="") {
        alert("Please provide user email address!");
        document.senda.recpt.focus();
        return false;
    }
    if(document.senda.amont.value<1) {
        alert("Please enter amount value greater than 0!");
        document.senda.amont.focus();
        return false;
    }
    if(document.senda.amont.value==="") {
        alert("Please provide amount to send!");
        document.senda.amont.focus();
        return false;
    }
    return (true);
}

function wit() {
    if(document.wi.cool.value==="") {
        alert("Please enter amount value greater than 0!");
        document.wi.cool.focus();
        return false;
    }
    return (true);
}

function depvlo() {
    if(document.dep.cool.value==="") {
        alert("Please enter amount value greater than 0!");
        document.dep.cool.focus();
        return false;
    }
    return (true);
}

function newvl() {
    if(document.new.type.value==="") {
        alert("Please select account type e.g. Phone,Bank or Paypal");
        document.new.type.focus();
        return false;
    }
    if(document.new.svc.value==="") {
        alert("Please provide service provider e.g. Stanbic Bank, Airtel, Paypal etc.");
        document.new.svc.focus();
        return false;
    }
    if(document.new.ctry.value==="") {
        alert("Please provide country e.g. Uganda");
        document.new.ctry.focus();
        return false;
    }
    if(document.new.bic.value==="" && document.new.type.value==="bank") {
        alert("Please provide SWIFT/BIC");
        document.new.bic.focus();
        return false;
    }
    if(document.new.acn.value==="") {
        alert("Please provide A/C number, Phone Or Paypal username/email");
        document.new.acn.focus();
        return false;
    }
    
    return (true);
}

function msgvl() {
    if(document.msge.mg.value==="") {
        alert("Please Provide your message! e.g. Hi!");
        document.msge.mg.focus();
        return false;
    }
    return (true);
}

function newmsgvl() {
    if(document.newmg.rec.value==="") {
        alert("Please Provide Recipiet! e.g. silas@gmail.com!");
        document.newmg.rec.focus();
        return false;
    }
    if(document.newmg.tit.value==="") {
        alert("Please Provide Subject!!");
        document.newmg.tit.focus();
        return false;
    }
    if(document.newmg.mg.value==="") {
        alert("Please Provide your message! e.g. Hi!");
        document.newmg.mg.focus();
        return false;
    }
    return (true);
}

function cpavl() {
    if(document.cp.pass.value==="") {
        alert("Please provide current active password!");
        document.cp.pass.focus();
        return false;
    }
    if(document.cp.pass.value!==document.cp.apass.value) {
        alert("Error! Incorrect active password!");
        document.cp.pass.focus();
        return false;
    }
    if(document.cp.newpass.value==="") {
        alert("Please provide new password!");
        document.cp.newpass.focus();
        return false;
    }
    if(document.cp.conpass.value==="") {
        alert("Please confirm new password!");
        document.cp.conpass.focus();
        return false;
    }
    if(document.cp.conpass.value!==document.cp.newpass.value) {
        alert("New Password does not match!");
        document.cp.conpass.focus();
        return false;
    }
    return (true);
}

function ctusvl() {
    if(document.ctus.mail.value==="") {
        alert("Please provide email!");
        document.ctus.mail.focus();
        return false;
    }
    if(document.ctus.sub.value==="") {
        alert("Please provide a Subject!");
        document.ctus.sub.focus();
        return false;
    }
    if(document.ctus.msg.value==="") {
        alert("Please provide your Message");
        document.ctus.msg.focus();
        return false;
    }
    return (true);
}

function rrsstt() {
    if(document.reset.emailo.value==="") {
        alert("Please provide your email");
        document.reset.emailo.focus();
        return false;
    }
    return (true);
}

function crtpl() {
    if(document.payrol.pname.value==="") {
        alert("Please enter Payroll Name!");
        document.payrol.pname.focus();
        return false;
    }
    if(document.payrol.com.value==="") {
        alert("Please enter Payroll Company!");
        document.payrol.com.focus();
        return false;
    }
    return (true);
}

function addpl() {
    if(document.adp.fname.value==="") {
        alert("Please provide Full Names");
        document.adp.fname.focus();
        return false;
    }
    if(document.adp.tit.value==="") {
        alert("Please provide Title");
        document.adp.tit.focus();
        return false;
    }
    if(document.adp.amt.value<1) {
        alert("Please provide Amount greater or equal to 1.00");
        document.adp.amt.focus();
        return false;
    }
    if(document.adp.mail.value==="") {
        alert("Please provide Email");
        document.adp.mail.focus();
        return false;
    }
    return (true);
}

function newappvl() {
    if(document.newapp.mname.value==="") {
        alert("Please provide App Name!");
        document.newapp.mname.focus();
        return false;
    }
    if(document.newapp.co.value==="") {
        alert("Please provide Company Name!");
        document.newapp.co.focus();
        return false;
    }
    return (true);
}

function vlpnow() {
    if(document.payi.email.value==="") {
        alert("Provide Your Email!");
        document.payi.email.focus();
        return false();
    }
    if(document.payi.pass.value==="") {
        alert("Provide Your Password!");
        document.payi.pass.focus();
        return false();
    }
    return (true);
}

//menu button click events
function showMenu() {
  document.getElementById("dropmenu").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
};
