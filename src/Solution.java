import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static class PhotoFile {
        final String name;
        final String ext;
        final String location;
        final Date dateTime;
        final String oldName;

        public PhotoFile(String oldName) {
            this.oldName = oldName;

            String[] fileDetails = oldName.split(",\\s*");
            if (fileDetails.length < 3) {
                throw new IllegalArgumentException("Not enough details for file " + oldName);
            }

            String fileName = fileDetails[0];
            this.name = fileName.substring(0, fileName.lastIndexOf("."));
            this.ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            this.location = fileDetails[1];

            try {
                this.dateTime = dateFormatter.parse(fileDetails[2]);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Incorrectly formatted datetime in the details for file " + oldName);
            }
        }
    }


    private static Map<String, List<PhotoFile>> getGroups(List<String> fileNames) {
        return fileNames.stream()
                .map(PhotoFile::new)
                .collect(Collectors.toMap(
                        photoFile -> photoFile.location,
                        Collections::singletonList,
                        (l1, l2) -> Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList())
                ));

    }


    public static String padNumber(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    private static Stream<? extends Map.Entry<String, String>> toOldNewNameEntry(Map.Entry<String, List<PhotoFile>> entry) {
        List<PhotoFile> locationPhotos = entry.getValue();
        int numLen = String.valueOf(locationPhotos.size()).length();

        locationPhotos.sort(Comparator.comparing(o -> o.dateTime));

        int num = 1;
        Map<String, String> entries = new HashMap<>();
        for (PhotoFile photoFile: locationPhotos) {
            String newName = entry.getKey()
                    + padNumber(String.valueOf(num), numLen)
                    + "."
                    + photoFile.ext;
            entries.put(photoFile.oldName, newName);
            num++;
        }
        return entries.entrySet().stream();
    }

    private static Map<String, String> getOldToNewNameMapping(List<String> originalFileNames) {
        Map<String, List<PhotoFile>> groupedPhotos = getGroups(originalFileNames);
        return groupedPhotos.entrySet()
                .stream()
                .flatMap(Solution::toOldNewNameEntry)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private static String getSortedPhotos(String S) {
        List<String> originalFileNames = Arrays.asList(S.split("\n+"));
        Map<String, String> oldToNewName = getOldToNewNameMapping(originalFileNames);
        ArrayList<String> result = new ArrayList<>();
        for (String oldName : originalFileNames) {
            String newName = oldToNewName.get(oldName);
            result.add(newName);
        }
        return String.join("\n", result);
    }


    public static void main(String[] args) {
        System.out.println(getSortedPhotos("photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11"));
    }

}