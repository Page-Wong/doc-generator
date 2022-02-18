import request from "@/utils/request";
//import qs from "qs";

export function readParam(file) {
  let formData = new FormData();
  formData.append("file", file);
  return request({
    url: "/upload/doc/param",
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
    method: "post",
    data: formData,
  });
}

export function generator(excelFile, docFiles) {
  let formData = new FormData();
  formData.append("excelFile", excelFile);
  docFiles.forEach((file) => {
    formData.append("docFile", file);
  });
  return request({
    url: "/upload/generator",
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
    method: "post",
    data: formData,
  });
}
