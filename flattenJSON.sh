JQ='/c/tools/jq-win64.exe'
cat <<EOF| $JQ '.' -c
{
  "id": "837d3acd-285e-478a-8d46-817df0a5b4d9",
  "name": "Google<br>",
  "url": "https://www.google.com \t",
  "tests": [{
    "id": "ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9",
    "name": "Google",
    "commands": [{
      "id": "160c2276-d9b3-4523-bdf3-b914111ca407",
      "comment": "",
      "command": "open",
      "target": "/images",
      "value": ""
    }, {
      "id": "856ac533-41f0-4091-813d-6f865cf72985",
      "comment": "",
      "command": "open",
      "target": "/",
      "value": ""
    }, {
      "id": "9c8e6eaf-3b39-435f-93f8-6716f159721d",
      "comment": "",
      "command": "windowMaximize",
      "target": "",
      "value": ""
    }, {
      "id": "4a0595ff-61c3-41fc-94fa-b338e5c65faf",
      "comment": "",
      "command": "highlight",
      "target": "id=lst-ib",
      "value": ""
    }, {
      "id": "602f45cf-a2f4-4491-b51c-a1036cebe879",
      "comment": "",
      "command": "pause",
      "target": "",
      "value": "200"
    }, {
      "id": "22d356ea-f33a-49b7-ab07-53ecba35cf65",
      "comment": "",
      "command": "click",
      "target": "//input[@type='text']",
      "value": ""
    }, {
      "id": "37f57010-4677-4bbf-8a1d-e0f19faadbb2",
      "comment": "",
      "command": "store",
      "target": "selenium ide",
      "value": "text"
    }, {
      "id": "47b767f5-a7d4-4332-bc4f-5e1e986cf732",
      "comment": "",
      "command": "type",
      "target": "id=lst-ib",
      "value": "${text}"
    }, {
      "id": "f2b9ac0a-7cce-4c0b-bb29-277eeea8bf12",
      "comment": "",
      "command": "verifyElementPresent",
      "target": "//input[@value='Google Search']",
      "value": ""
    }, {
      "id": "5bfa6d57-3039-4a5a-82c1-e56dabb3cae5",
      "comment": "",
      "command": "verifyText",
      "target": "id=SIvCob",
      "value": "Google offered in:"
    }, {
      "id": "673164e5-5802-4763-9dbe-f6afef86bef8",
      "comment": "",
      "command": "sendKeys",
      "target": "id=lst-ib",
      "value": "${KEY_ENTER}"
    }, {
      "id": "82eb4147-efe2-4965-b71a-3132e3f7651b",
      "comment": "",
      "command": "refresh",
      "target": "",
      "value": ""
    }, {
      "id": "b269914c-4a15-40c3-b467-8c36c01d331b",
      "comment": "",
      "command": "clickAt",
      "target": "id=logo",
      "value": ""
    }, {
      "id": "f129939b-8f3b-4220-92a7-c4ecb4e9aff8",
      "comment": "",
      "command": "verifyTitle",
      "target": "Google",
      "value": ""
    }, {
      "id": "1a503afd-367b-4f28-a8b1-01d489da5d81",
      "comment": "",
      "command": "storeTitle",
      "target": "",
      "value": "var1"
    }, {
      "id": "768c0d16-b6bf-48e1-a424-b3248ebd088f",
      "comment": "",
      "command": "echo",
      "target": "${var1}",
      "value": ""
    }]
  }],
  "suites": [{
    "id": "05e89807-cb33-4ca6-8ca4-10e1cdf127c3",
    "name": "Default Suite",
    "tests": ["ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9"]
  }],
  "urls": ["https://www.google.co.in", "https://www.google.co.in"]
}
EOF

