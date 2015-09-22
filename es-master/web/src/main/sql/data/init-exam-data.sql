UPDATE `exam_subject` t SET t.content = replace(t.content,'&nbsp;','');
UPDATE `exam_subject`  SET type =  substr(type,(instr(type,'.')+1));
UPDATE `exam_answer` SET remark = '';

TRUNCATE  TABLE `exam_subject_bak`;
INSERT INTO `exam_subject_bak`
  SELECT MIN(id) , MIN( subject_id ) , content, answer,
    TYPE , category, MAX( remark )
  FROM  `exam_subject`
  GROUP BY content, answer,
    TYPE , category;

TRUNCATE TABLE `exam_answer_bak`;
INSERT INTO  `exam_answer_bak`
SELECT *
FROM exam_answer t
WHERE EXISTS (
    SELECT 1
    FROM exam_subject_bak t1
    WHERE t.subject_id = t1.subject_id
);

UPDATE  `exam_answer_bak` t SET t.flag = TRUE WHERE EXISTS (
    SELECT 1
    FROM  `exam_subject_bak` t1
    WHERE t1.subject_id = t.subject_id
          AND INSTR( t1.answer, t.serial_no ) >0
);

/*
SELECT *
FROM  `exam_subject_bak` t
WHERE NOT
      EXISTS (

          SELECT 1
          FROM  `exam_answer_bak` t1
          WHERE t.subject_id = t1.subject_id
                AND t1.flag =
                    TRUE
      )
      AND t.type <>  '判断题'
LIMIT 0 , 30
*/