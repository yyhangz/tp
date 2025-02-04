package gomedic.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import gomedic.commons.exceptions.IllegalValueException;
import gomedic.commons.util.JsonUtil;
import gomedic.model.AddressBook;
import gomedic.model.activity.exceptions.ConflictingActivityException;
import gomedic.testutil.Assert;
import gomedic.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_ADDRESS_BOOK_FILE = TEST_DATA_FOLDER.resolve("typicalAddressBook.json");
    private static final Path INVALID_USER_PROFILE_FILE =
            TEST_DATA_FOLDER.resolve("invalidUserProfileAddressBook.json");
    private static final Path INVALID_PATIENT_FILE = TEST_DATA_FOLDER.resolve("invalidPatientAddressBook.json");
    private static final Path INVALID_DOCTOR_FILE = TEST_DATA_FOLDER.resolve("invalidDoctorAddressBook.json");
    private static final Path INVALID_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("invalidActivityAddressBook.json");
    private static final Path CONFLICTING_ACTIVITY_FILE = TEST_DATA_FOLDER
            .resolve("conflictingActivitiesAddressBook.json");
    private static final Path DUPLICATE_ACTIVITY_FILE = TEST_DATA_FOLDER.resolve("duplicateActivitiesAddressBook.json");
    private static final Path DUPLICATE_DOCTOR_FILE = TEST_DATA_FOLDER.resolve("duplicateDoctorAddressBook.json");
    private static final Path DUPLICATE_PATIENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientAddressBook.json");

    @Test
    public void toModelType_typicalAddressBookFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ADDRESS_BOOK_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_conflictingActivity_throwsConflictingActivityException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(CONFLICTING_ACTIVITY_FILE,
                JsonSerializableAddressBook.class).get();

        Assert.assertThrows(ConflictingActivityException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateActivity_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ACTIVITY_FILE,
                JsonSerializableAddressBook.class).get();

        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidActivity_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_ACTIVITY_FILE,
                JsonSerializableAddressBook.class).get();

        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidUserProfile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_USER_PROFILE_FILE,
                JsonSerializableAddressBook.class).get();

        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidDoctorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_DOCTOR_FILE,
                JsonSerializableAddressBook.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateDoctors_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_DOCTOR_FILE,
                JsonSerializableAddressBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_DOCTOR,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidPatientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_PATIENT_FILE,
                JsonSerializableAddressBook.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePatients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PATIENT_FILE,
                JsonSerializableAddressBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_PATIENT,
                dataFromFile::toModelType);
    }

}
