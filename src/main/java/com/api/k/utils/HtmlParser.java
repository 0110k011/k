package com.api.k.utils;

import com.api.k.dtos.TransactionDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {

    public String nfParse(String html) {

        TransactionDto transactionDto = new TransactionDto();
        Pattern pattern = Pattern.compile("#xml-nfe-container.*?(<.*?/html>)");
        Matcher matcher = pattern.matcher(html);

        String nf = matcher.group(1);

        Pattern patternProductServiceData = Pattern.compile("ao\"><span>(.*?)<.*?<span>(.*?)<.*?<span>(.*?)<.*?<span>(.*?)<");
        Matcher matcherProductServiceData = pattern.matcher(nf);

        transactionDto.setName("Nome de teste");

        return transactionDto.toString();
    }

}
