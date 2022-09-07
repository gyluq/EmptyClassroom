function doHighlight(bodyText, searchTerm, highlightStartTag, highlightEndTag) {
    // the highlightStartTag and highlightEndTag parameters are optional
    if ((!highlightStartTag) || (!highlightEndTag)) {
        highlightStartTag = "<font style='color:blue; background-color:yellow;'>";
        highlightEndTag = "</font>";
    }

    var newText = "";
    var i = -1;
    var lcSearchTerm = searchTerm.toLowerCase();
    var lcBodyText = bodyText.toLowerCase();

    while (bodyText.length > 0) {
        i = lcBodyText.indexOf(lcSearchTerm, i + 1);
        if (i < 0) {
            newText += bodyText;
            bodyText = "";
        } else {
            // skip anything inside an HTML tag
            if (bodyText.lastIndexOf(">", i) >= bodyText.lastIndexOf("<", i)) {
                // skip anything inside a <script> block
                if (lcBodyText.lastIndexOf("/script>", i) >= lcBodyText.lastIndexOf("<script", i)) {
                    newText += bodyText.substring(0, i) + highlightStartTag + bodyText.substr(i, searchTerm.length) + highlightEndTag;
                    bodyText = bodyText.substr(i + searchTerm.length);
                    lcBodyText = bodyText.toLowerCase();
                    i = -1;
                }
            }
        }
    }
    return newText;
}

function highlightSearchTerms(searchText, treatAsPhrase, warnOnFailure, highlightStartTag, highlightEndTag) {
    if (treatAsPhrase) {
        searchArray = [searchText];
    } else {
        searchArray = searchText.split(" ");
    }

    if (!document.body || typeof (document.body.innerHTML) == "undefined") {
        if (warnOnFailure) {
            alert("the text is unavailable.");
        }
        return false;
    }

    var bodyText = document.body.innerHTML;
    for (var i = 0; i < searchArray.length; i++) {
        bodyText = doHighlight(bodyText, searchArray[i], highlightStartTag, highlightEndTag);
    }

    document.body.innerHTML = bodyText;
    return true;
}

function searchPrompt(defaultSearchText, isPrompt, treatAsPhrase, textColor, bgColor) {
    // we can optionally use our own highlight tag values
    if ((!textColor) || (!bgColor)) {
        highlightStartTag = "";
        highlightEndTag = "";
    } else {
        highlightStartTag = "<font style='color:" + textColor + "; background-color:" + bgColor + ";'>";
        highlightEndTag = "</font>";
    }

    if (treatAsPhrase) {
        promptText = "Please enter the phrase you'd like to highlight:";
    } else {
        promptText = "Please enter the words you'd like to highlight, separated by spaces:";
    }
    if (isPrompt)
        // defaultSearchText = prompt(promptText, defaultSearchText);
        defaultSearchText = "101 102 103 104 201 202 203 204 301 302 303 304 215 315";

    if (!defaultSearchText) {
        alert("No search terms were entered. Exiting function.");
        return false;
    }

    return highlightSearchTerms(defaultSearchText, treatAsPhrase, true, highlightStartTag, highlightEndTag);
}
