//去前后空格
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

//判断字符串是否为空，空返回true
function isBlank(str) {
	return !isNotBlank(str);
}

//判断字符串是否为空，空返回false
function isNotBlank(str) {
	str = str.trim();
	if (str === undefined || str == "") {
		return false;
	}
	return true;
}