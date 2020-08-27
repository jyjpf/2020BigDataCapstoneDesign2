<template>
<v-container fluid :grid-list-md="!$vuetify.breakpoint.xs" :class="$vuetify.breakpoint.xs ? 'pa-0' : ''">
  <v-card class="mx-auto">
    <v-toolbar
      color="purple"
      dark
      flat
    >
      <template v-slot:extension>
        <v-tabs
          v-model="tabs"
          centered
        >
          <v-tab
            v-for="courseTab in courseTabs"
            :key="courseTab"
          >
            {{courseTab}}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>
 
          
       
    <v-tabs-items v-model="tabs">
      <v-tab-item>
        <v-card>
             <v-select
              class="float-left mt-6"
              v-model="select"
              :items="sign"
              label="승인구분"
            ></v-select>
             <v-select
              class="float-left mt-6"
              v-model="select"
              :items="identi"
              label="신분"
            ></v-select>
             <v-select
              class="float-left mt-6"
              v-model="select"
              :items="sex"
              label="성별"
            ></v-select>
             <v-select
              class="float-left mt-6"
              v-model="select"
              :items="age"
              label="학년"
            ></v-select>
          <v-card-title>
             <v-select
              class="float-left mt-6"
              v-model="select"
              :items="ban"
              label="반"
            ></v-select>
          </v-card-title>
                      <v-text-field
              v-model="search"
              label="검색"
              single-line
              hide-details
            ></v-text-field>
            <br>
        <v-flex class="text-right">
        <v-btn width="100" color="#FSFSFS" x-medium @click="submit()">엑셀업로드</v-btn>
        <v-btn width="100" color="#FSFSFS" x-medium @click="submit()">승인</v-btn>
    </v-flex>
          <!-- 회원관리-->
          <v-data-table
          v-model="selected"
          :headers="headers"
          :items="lectures"
          :search="search2"
          :single-select="singleSelect"
          item-key="user_id"
          show-select
          sort-by="title"
          class="elevation-1"
          >
        <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>회원관리</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
            >추가하기</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.position_cd" label="신분구분코드"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.user_id" label="유저 아이디"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.kor_nm" label="한국이름"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. gender_cd" label="성별"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.birth_dt" label="생년월일"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.grade" label="학년"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.ban" label="반"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.cel_phone_no" label="전화번호"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. register_dt" label="가입일"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
    </v-data-table>
        </v-card>
      </v-tab-item>
     <v-tab-item>
          <v-card class="mx-auto">
    <v-card-title>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="검색어를 입력하세요"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <!-- 년도/학기-->
    <v-data-table
      v-model="selected"
      :headers="headers2"
      :items="rows"
      :search="search2"
      :single-select="singleSelect"
      item-key="user_id"
      show-select
      class="elevation-1"
    >
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>년도/학기</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
            >추가하기</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.position_cd" label="신분구분코드"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.user_id" label="유저 아이디"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.kor_nm" label="한국이름"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. gender_cd" label="성별"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.birth_dt" label="생년월일"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.grade" label="학년"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.ban" label="반"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.cel_phone_no" label="전화번호"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. register_dt" label="가입일"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
    </v-data-table>
    </v-card>
    </v-tab-item>
         <v-tab-item>
           <!-- 대코드-->
     <v-card class="mx-auto">
      <v-spacer></v-spacer>
       <v-data-table
        v-model="selected"
        :headers="headers3"
        :items="rows2"
        :single-select="singleSelect"
        item-key="user_id"
        show-select
        class="elevation-1"
       >
       <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>대코드</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
            >추가하기</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.position_cd" label="신분구분코드"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.user_id" label="유저 아이디"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.kor_nm" label="한국이름"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. gender_cd" label="성별"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.birth_dt" label="생년월일"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
       </v-data-table>
     </v-card>
    </v-tab-item>
         <v-tab-item>
           <!-- 소코드-->
     <v-card class="mx-auto">
      <v-spacer></v-spacer>
       <v-data-table
        v-model="selected"
        :headers="headers4"
        :items="rows3"
        :single-select="singleSelect"
        item-key="user_id"
        show-select
        class="elevation-1"        
       >
       <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>소코드</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              dark
              class="mb-2"
              v-bind="attrs"
              v-on="on"
            >추가하기</v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.position_cd" label="신분구분코드"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.user_id" label="유저 아이디"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.kor_nm" label="한국이름"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. gender_cd" label="성별"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.birth_dt" label="생년월일"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.grade" label="학년"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.ban" label="반"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem.cel_phone_no" label="전화번호"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6" md="4">
                    <v-text-field v-model="editedItem. register_dt" label="가입일"></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="save">Save</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
       </v-data-table>
     </v-card>
    </v-tab-item>
    </v-tabs-items>
  </v-card>
</v-container>
</template>
<script lang="javascript" src="dist/xlsx.full.min.js"></script>
<script>
import router from '../router'
  export default {
    
    data () {
      return {
        tabs: null,
        dialog: false,
        editedIndex: -1,
        desserts: [],
        editedItem: {
        position_cd: '',
        user_id: '',
        kor_nm: '',
        gender_cd: '',
        birth_dt: '',
        grade:0,
        ban:'',
        cel_phone_no:'',
        register_dt:'',
        },
      defaultItem: {
        position_cd: '',
        user_id: '',
        kor_nm: '',
        gender_cd: '',
        birth_dt: '',
        grade:0,
        ban:'',
        cel_phone_no:'',
        register_dt:'',
        },
        sign:["신청", "대기중", "승인완료"],
        identi:["학생", "선생님", "관리자"],
        sex:["남", "여"],
        age:["1학년", "2학년","3학년","4학년","5학년","6학년"],
        ban:["1반","2반","3반","4반","5반","6반","7반","9반","10반","11반"],
        search: '',
        courseTabs: ["회원관리", "년도/학기", "대코드", "소코드"],
        headers: [
          { text: '신분', value: 'position_cd' },
          { text: '아이디', value: 'user_id' },
          { text: '이름', value: 'kor_nm', sortable: false },
          { text: '성별', value: 'gender_cd' },
          { text: '생년월일', value: 'birth_dt' },
          { text: '학년', value: 'grade' },
          { text: '반', value: 'ban' },
          { text: '연락처', value: 'cel_phone_no' },
          { text: '가입일', value: 'register_dt' },
          { text: 'Actions', value: 'actions', sortable: false },
        ],
        headers2: [
          { text: '년도', value: 'grade' },
          { text: '학기', value: 'teacher_nm' },
          { text: '관리시작일', value: 'enroll_ed_dt' },
          { text: '관리종료일', value: 'actions', sortable: false },
          { text: '사용여부', value: 'enroll_ed_dt' },
        ],
        headers3: [
          {text: '대코드', value: 'daecode'},
          {text: '대코드명', value: 'daecodename'},
          {text: '비고', value: 'bigo'},
        ],
        headers4: [
          {text: '소코드', value: 'socode'},
          {text: '소코드명', value: 'socodename'},
          {text: '비고', value: 'bigo'},
        ],
        lectures:[
          
        ],
        selected: [],
      }
    },
    //관리자 페이지 편집기능
    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    created () {
      this.initialize()
    },
    //관리자 페이지 편집 기능여기까지
    
    //회원목록 불러오기
    created(){
    this.$http.get('/api/admin/user/list').then(res =>{
          console.log('status code: ${res.ban}');
          this.lectures=res.data;
          //console.log(res);
          //alert(JSON.stringify(this.lectures));
    }
    )
  },
    methods: {
      initialize () {
        this.notice = [
        ]
      },
      editItem (item) {
        this.editedIndex = this.desserts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      deleteItem (item) {
        const index = this.desserts.indexOf(item)
        confirm('Are you sure you want to delete this item?') && this.desserts.splice(index, 1)
      },
      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
      save () {
        if (this.editedIndex > -1) {
          Object.assign(this.lectures[this.editedIndex], this.editedItem)
        } else {
          this.lectures.push(this.editedItem)
        }
        this.close()
      },
      //회원관리 저장
      gonewstudy(){
        router.push({name: 'stwr'});
      },
      //년도/학기 저장
      yearsave(){
        router.push({name: 'stwr'});
      },
      //대코드 저장
      daecodesave(){
        router.push({name: 'stwr'});
      },
      //소코드 저장
      socodesave(){
        router.push({name: 'stwr'});
      },
      //로그인 화면으로가기
      gotmain(){
        router.push({name: 'Home'});
      },
      //엑셀 업로드테스트 엑셀 결과를 lectures으로 보냄 https://godd.tistory.com/41
      importExcel(event) {
      const file = event.target.files[0];
      let reader = new FileReader();
      reader.onload = (e) => {
        let data = reader.result;
        // let data = e.target.result;
        let workbook = XLSX.read(data, {type: 'binary'});
        workbook.SheetNames.forEach(sheetName => {
            const roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName]);
            this.lectures.push(roa);
          });
      };
      reader.readAsBinaryString(file);
      },
    }
  }
</script>