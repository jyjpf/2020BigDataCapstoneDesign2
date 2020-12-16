<template>
  <v-dialog
    v-model="showEditor"
    no-click-animation
    persistent
    width="800"
    content-class="dialog-center"
  > 
    <v-card>
      <v-card-title class="pa-2 orange lighten-2">
        {{ boardType.text }}
        <v-spacer/>
        <v-btn
        class="선생님"
        v-if="boardType.value == '006001'"
        icon 
        @click="modify"
        >
          수정
        </v-btn>
                <v-btn
        class="선생님"
        v-if="boardType.value == '006001'"
        icon 
        @click="modify2222"
        >
          수정취소
        </v-btn>
        <v-btn 
        v-if="boardType.value == '006001'"
        class="선생님"
        icon
        @click="remove"
        >
          삭제
        </v-btn>
        <v-btn 
        v-if="boardType.value == '006002'"
        class="학생"
        icon
        @click="remove"
        >
        학생수정
        </v-btn>
        <v-btn 
        v-if="boardType.value == '006002'"
        class="학생"
        icon
        @click="remove"
        >
        학생삭제
        </v-btn>
        <v-btn icon @click="$emit('close-dialog')">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </v-card-title>

      <v-divider></v-divider>

      <div class="email-editor">
        <v-divider></v-divider>
        <div v-if="create">
          <v-text-field
            v-model="title"
            label="제목"
            solo
            flat
            hide-details
          />
        </div>
        <div v-else class="pa-2">
          <h2>{{ title }}</h2>
          <v-divider class="ma-1" />
          <span v-html="content"/>
        </div>
        <v-divider/>

        <editor-menu-bar v-if="create" v-slot="{ commands, isActive }" :editor="editor">
          <div class="pa-1">
            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.bold() }"
              @click="commands.bold"
            >
              <v-icon>mdi-format-bold</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.italic() }"
              @click="commands.italic"
            >
              <v-icon>mdi-format-italic</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.strike() }"
              @click="commands.strike"
            >
              <v-icon>mdi-format-strikethrough</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.underline() }"
              @click="commands.underline"
            >
              <v-icon>mdi-format-underline</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.paragraph() }"
              @click="commands.paragraph"
            >
              <v-icon>mdi-format-paragraph</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.heading({ level: 1 }) }"
              @click="commands.heading({ level: 1 })"
            >
              H1
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.heading({ level: 2 }) }"
              @click="commands.heading({ level: 2 })"
            >
              H2
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.heading({ level: 3 }) }"
              @click="commands.heading({ level: 3 })"
            >
              H3
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.bullet_list() }"
              @click="commands.bullet_list"
            >
              <v-icon>mdi-format-list-bulleted</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.ordered_list() }"
              @click="commands.ordered_list"
            >
              <v-icon>mdi-format-list-numbered</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.blockquote() }"
              @click="commands.blockquote"
            >
              <v-icon>mdi-format-quote-close</v-icon>
            </v-btn>

            <v-btn
              icon
              tile
              :class="{ 'is-active': isActive.code_block() }"
              @click="commands.code_block"
            >
              <v-icon>mdi-code-tags</v-icon>
            </v-btn>

            <v-btn icon tile @click="commands.horizontal_rule">
              <v-icon>mdi-minus</v-icon>
            </v-btn>

            <v-btn icon tile @click="commands.undo">
              <v-icon>mdi-undo</v-icon>
            </v-btn>

            <v-btn icon tile @click="commands.redo">
              <v-icon>mdi-redo</v-icon>
            </v-btn>

          </div>
        </editor-menu-bar>

        <v-divider></v-divider>

        <editor-content v-if="create" class="editor__content pa-3 py-4" :editor="editor"/>

        <v-divider></v-divider>

        <div v-if="create" class="d-flex align-center pa-2">
          <v-btn color="primary" @click="submit(true)">
            저장
          </v-btn>
          <v-btn v-if="this.modify2 == true" color="primary" @click="submit(true)">
            저장2
          </v-btn>
          <v-file-input
            v-model="file"
            :rules="rules"
            show-size
            dense
            hide-details="auto"
            class="pa-0 mx-1"
          />
        </div>
        <div v-else class="pa-1">
          <div v-if="fileName">
            <v-icon>mdi-file-document-outline</v-icon>
            <a ref="download" href="#" class="mx-1" @click="download()">{{ fileName }}</a>
            <v-divider class="ma-1"/>
          </div>
          <div v-for="(item, index) in comments" :key="index" class="pa-1">
            <v-icon class="mr-1">mdi-subdirectory-arrow-right</v-icon>
            <span>{{ item.content }} - {{ item.auth_nm }}</span>
            <template>
          <v-dialog
            v-model="dialog"
            width="500"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                text 
                small
                v-bind="attrs"
                v-on="on"
              >
                댓글 수정
              </v-btn>
            </template>

            <v-card>
            <v-card-title class="headline grey lighten-2">
              댓글 수정
            </v-card-title>
            <v-text-field
            label="수정할 내용을 입력하세요"
            flat
            hide-details
            >
            <template v-slot:prepend>
              <v-icon>mdi-subdirectory-arrow-right</v-icon>
            </template>
          </v-text-field>

              <v-divider></v-divider>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                color="primary"
                text
                  @click="dialog = false"
                >
                댓글 저장
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          </template>
            <v-btn
            :value="item.seq_no"
            text 
            small
            @click="removeComment(item)"
            >댓글 삭제</v-btn>
          </div>
          <v-text-field
            v-model="comment"
            label="댓글"
            flat
            hide-details
          >
            <template v-slot:prepend>
              <v-icon>mdi-subdirectory-arrow-right</v-icon>
            </template>
            <template v-slot:append-outer>
              <v-btn color="primary" @click="submit(false)">
                저장
              </v-btn>
            </template>
          </v-text-field>

        </div>
      </div> 
    </v-card>
  </v-dialog>
</template>

<script>
import axios from 'axios'
import { Editor, EditorContent, EditorMenuBar } from 'tiptap'
import {
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History
} from 'tiptap-extensions'
/*
|---------------------------------------------------------------------
| 게시판 에디터 통합 Component
|---------------------------------------------------------------------
*/
export default {
  components: {
    EditorContent,
    EditorMenuBar
  },
  props: {
    // Show compose dialog
    showEditor: {
      type: Boolean,
      default: false
    },
    boardType: {
      type: Object,
      default: null
    },
    boardData: {
      type: Object,
      default: null
    }
  },
  data () {
    return {
      modify2: false,
      contents: [],
      create: false,
      title: '',
      content: '',
      file: null,
      fileName: '',
      fileHash: '',
      rules: [
        (file) => !file || file.size < 20000000 || '파일 크기는 20 MB를 넘을 수 없습니다!'
      ],
      editor: new Editor({
        extensions: [
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History()
        ]
      }),
      comments: [],
      comment: '',
      commentremove: '',
      dialog: false
    }
  },
  created() {
    this.init(true)
  },
  beforeDestroy() {
    this.editor.destroy()
  },
  methods: {
    init(init) {
      if (this.boardData) {
        const { lecture_no, no, input_id } = this.boardData

        axios.get(`/api/lecture/${lecture_no}/board/${no}`)
          .then((res) => {
            if (init) {
              this.title = res.data[0].title
              this.content = res.data[0].content
              this.fileName = res.data[0].file_nm
              this.fileHash = res.data[0].save_file_nm
              console.log(res.data)
            }
            if (res.data.length > 1)
              this.comments = res.data.slice(1)
            console.log('seq_no= ' + this.comments.length)
          })
        this.create = false
      } else {
        this.create = true
      }
      console.log('1')
    },
    async submit(board) {
      if (board) {
        const formData = new FormData()

        formData.append('title', this.title)
        formData.append('content', this.editor.getHTML())
        formData.append('board_cd', this.boardType.value)
        formData.append('file', this.file)

        await axios.post(`/api/lecture/${this.$route.params.lecture}/board`, formData)
        
        this.$emit('submit-dialog')
      } else {

        const { lecture_no, board_cd, no } = this.boardData

        await axios.post(`/api/lecture/${lecture_no}/comment`, {
          lecture_no: lecture_no,
          board_cd: board_cd,
          no: no,
          content: this.comment
        })
        this.init(false)
      }
    },
    async download() {
      // TODO: 파일 내려받는 프로세스 직접 구현
      const file = await axios.get(`/api/download/board/${this.fileHash}`, { responseType: 'blob' })
      const blob = new Blob([file.data])
      const fileUrl = URL.createObjectURL(blob)
      const link = document.createElement('a')

      link.href = fileUrl
      link.setAttribute('download', this.fileName)
      document.body.appendChild(link)
      link.click()
      URL.revokeObjectURL(fileUrl)
    },
    modify() {
      this.create = true
      this.modify2 = true
    },
    modifyComment() {
      const { lecture_no, board_cd, no } = this.boardData

      axios.post(`/api/lecture/${lecture_no}/comment`, {
        lecture_no: lecture_no,
        board_cd: board_cd,
        no: no,
        content: this.comment
      })
    },
    removeComment(item) {
      const { lecture_no, no, input_id,seq_no } = this.boardData
      
      //comments.seq_no = this.comments
      //console.log('comment= ' + JSON.stringify(this.comments.length.seq_no))
      //console.log('댓글 내용' + this.comments)
      //console.log('댓글 내용 seq ' + JSON.stringify(this.comment))
      //console.log('댓글 내용 seq ' + JSON.stringify(this.commentremove))
      //console.log('seq_no=댓글 ' + JSON.stringify(this.comments))
      axios.delete(`/api/lecture/${lecture_no}/board/${no}/${item.seq_no}`)
        .then((res) => { 
          console.log(res.data) })
      console.log(item.seq_no + ' 댓글삭제')
    },
    remove() {
      const { lecture_no, no } = this.boardData
      
      axios.delete(`/api/lecture/${lecture_no}/board/${no}`)
        .then((res) => { 
          console.log(res.data) })
      this.$emit('submit-dialog')
    }
  }
}
</script>

<style lang="scss">
.dialog-bottom {
  position: fixed;
  bottom: 10px;
  right: 10px;
}
.email-editor {
  position: relative;
  .v-btn {
    &.is-active {
      background-color: #f1f1f1;
    }
  }
  .editor__content {
    overflow-wrap: break-word;
    word-wrap: break-word;
    word-break: break-word;
    * {
      caret-color: currentColor;
    }
    .ProseMirror {
      &:focus {
        outline: none;
      }
    }
    ul,
    ol {
      padding-left: 1rem;
    }
    li > p,
    li > ol,
    li > ul {
      margin: 0;
    }
    a {
      color: inherit;
    }
    blockquote {
      border-left: 3px solid rgba(0, 0, 0, 0.1);
      color: rgba(0, 0, 0, 0.8);
      padding-left: 0.8rem;
      font-style: italic;
      p {
        margin: 0;
      }
    }
    img {
      max-width: 100%;
      border-radius: 3px;
    }
    table {
      border-collapse: collapse;
      table-layout: fixed;
      width: 100%;
      margin: 0;
      overflow: hidden;
      td,
      th {
        min-width: 1em;
        border: 2px solid #fafafa;
        padding: 3px 5px;
        vertical-align: top;
        box-sizing: border-box;
        position: relative;
        > * {
          margin-bottom: 0;
        }
      }
      th {
        font-weight: bold;
        text-align: left;
      }
      .selectedCell:after {
        z-index: 2;
        position: absolute;
        content: "";
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        background: rgba(200, 200, 255, 0.4);
        pointer-events: none;
      }
      .column-resize-handle {
        position: absolute;
        right: -2px;
        top: 0;
        bottom: 0;
        width: 4px;
        z-index: 20;
        background-color: #adf;
        pointer-events: none;
      }
    }
    .tableWrapper {
      margin: 1em 0;
      overflow-x: auto;
    }
    .resize-cursor {
      cursor: ew-resize;
      cursor: col-resize;
    }
  }
}
</style>
