const multer = require('multer');
const path = require('path');
const userProfile_storage = multer.diskStorage({
  destination: (req, file, cb) => {
    cb(null, 'images/image_user');
  },
  filename: (req, file, cb) => {
    cb(null, file.fieldname + '_' + Date.now() + path.extname(file.originalname));
    console.log(file);
  },
});

module.exports = multer({
  storage: userProfile_storage,
  limit: {
    fileSize: 1000000, // 1000000 Bytes = 1 MB
  },
}).single('user_Image');
