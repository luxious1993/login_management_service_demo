package com.luxious.lmsd.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectId;
import com.amazonaws.services.s3.model.S3ObjectIdBuilder;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class S3FileReader {
    private final String BAD_WORD_FILE = "your file name";
    private final String BAD_WORD_BUCKET = "you bucket name";

    @Autowired
    private AmazonS3 amazonS3;

    private Logger log = LoggerFactory.getLogger(S3FileReader.class);

    private Stream<String> readFileLines() {
        final GetObjectRequest request = new GetObjectRequest(BAD_WORD_FILE, BAD_WORD_BUCKET);
        final S3Object s3Object = amazonS3.getObject(request);
        try (BufferedReader bufferedReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     s3Object.getObjectContent(), Charsets.UTF_8)))
        {
            return bufferedReader.lines()
                    .collect(Collectors.toCollection(ArrayList::new))
                    .stream();
        } catch (IOException e) {
            log.error("cannot to downloda {} from {}", BAD_WORD_FILE, BAD_WORD_BUCKET, e);
            throw new UncheckedIOException(e);
        }
    }

    public Set<String> getBadWordLogins() {
        return readFileLines()
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(HashSet::new));
    }
}
